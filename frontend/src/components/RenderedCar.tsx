import {useEffect, useState} from "react";
import axios from "axios";
import {CarBackseat} from "./CarBackseat.tsx";
import type {CarObject} from "../types/Car.ts";
import {routerConfig} from "../routerConfig.ts";
import {useNavigate, useParams} from "react-router-dom";
import {formatDate} from "../utils/formatDate.ts";


export function RenderedCar(props){
    const [driver,setDriver]= useState("");
    const [shotgun,setShotgun]= useState("");
    const [rear,setRearender]= useState([]);
    const navigate = useNavigate();
    let params = useParams();
    const isInThePast = props.isInThePast;

    const nonoImage = "http://localhost:5173/src/assets/nono.png";

    function setRear(){
        axios.get("/api/user/getAllUsers")
            .then((response)=>{
                response.data.map((e)=>{
                    const users = response.data;

                    const driverUser = users.find((u) => u.id == props.car.driverId);
                    if (driverUser) setDriver(driverUser);
                    const shotgunUser = users.find((u) => u.id == props.car.shotgun);
                    let seatsLeft=props.car.availableSeats - (shotgunUser!==undefined?1:0);

                    const rearUsers = props.car.rear
                        .map(id => users.find(u => u.id === id))
                        .filter(Boolean);
                    const seatedRearUsers=rearUsers.length;
                    if(seatedRearUsers<seatsLeft){
                        for(let i=seatedRearUsers;i<seatsLeft;i++){
                            rearUsers[i] = {"id":"","avatar_url":"http://localhost:5173/src/assets/free.png","name":"free"}
                        }
                    }
                    if(rearUsers.length<3){
                        for(let i=rearUsers.length;i<3;i++){
                            rearUsers[i] = {"id":"","avatar_url":undefined,"name":"nono"}
                        }
                    }

                    if (shotgunUser) {
                        setShotgun(shotgunUser);
                    }else{
                        let shotgunObject = {"id":"","avatar_url":"http://localhost:5173/src/assets/free.png","name":"free"};
                        if(seatedRearUsers>=seatsLeft){
                            shotgunObject = {"id":"","avatar_url":undefined,"name":"nono"};
                        }
                        setShotgun(shotgunObject);
                    }
                    setRearender(rearUsers);
                });

            });
    }

    function updateCar(car:CarObject,position:string,isSat:boolean){
        let updatedCar = props.car;
        if(position==="rear") {
            if (isSat) {
                updatedCar.rear = updatedCar.rear.filter(existingId => existingId !== String(props.user.id));
            } else {
                updatedCar.rear.push(String(props.user.id));
            }
        }else{
            if (isSat) {
                updatedCar.shotgun = "";
            } else {
                updatedCar.shotgun = String(props.user.id);
            }
        }

        axios.put("/api/cars/updatePassengers", updatedCar,{
            headers: {
                "Content-Type" : "application/json",
            }
        }).then(() => {
            setRear();
        });
    }

    function seatOutput(seatData,position:string){
        let returnContent;
        if(seatData.avatar_url!==undefined){
            let imgString = <img src={seatData.avatar_url} />;
            if(seatData.name==="free" && !isInThePast){
                returnContent =
                    <a onClick={()=>updateCar(props.car,position,false)} title={"Mitfahren"}>
                        {imgString}
                    </a>;
            }else{
                if(seatData.id===String(props.user.id) && !isInThePast){
                    returnContent =
                        <a onClick={()=>updateCar(props.car,position,true)} title={"Doch nicht mitfahren"}>
                            {imgString}
                        </a>;
                }else{
                    returnContent = imgString;
                }
            }
        }else{
            returnContent = <img src={nonoImage} />;
        }
        return returnContent;
    }
    function removeCar(car:CarObject){
        axios.delete("/api/cars/deleteCar/"+car)
            .then((response) => {
                props.loadCars();
            });
    }
    useEffect(()=>{
        setRear();
    },[]);

    let killButton;
    let editButton;
    if(props.car.driverId === props.user.id && !isInThePast){
      killButton = <button className={"killTheCar"} onClick={()=>{
          let r = confirm("Bist du sicher? Alle deine Mitfahrer werden obdachlos...")
          if(r){
              removeCar(props.car.id)
          }
      }}><img src={"../src/assets/trash.png"} /></button>;
      editButton = <button className={"editTheCar"} onClick={()=>{
          navigate(`${routerConfig.URL.TURNIER}/${params.name}/${props.car.id}`);
      }}><img src={"../src/assets/edit.png"} /></button>;
    }else{
        killButton="";
        editButton="";
    }



    return (
        <div className={"car"} key={props.car.driverId}>
            {killButton}
            {editButton}
            <div className="goTime">
                <b>Abfahrtzeit:</b> {formatDate(props.car.takeOffTime)}
            </div>
            <div className={"frontRow"}>
                <div className={"rider driver"}>
                    <img src={driver.avatar_url} alt="" />
                </div>
                <div className={"rider shotgun"}>
                    {seatOutput(shotgun,"shotgun")}
                </div>
            </div>
            <div className={"backRow"}>
                {
                    rear.map((e,index)=>{
                        return <CarBackseat key={index} seatData={e} index={index}  user={props.user} car={props.car} setRear={setRear} updateCar={updateCar} seatOutput={seatOutput} tournament={props.tournament} />
                    })
                }
            </div>
            <div style={{clear:"both"}} />
        </div>
    )
}