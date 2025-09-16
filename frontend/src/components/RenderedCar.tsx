import {useEffect, useState} from "react";
import axios from "axios";
import {CarBackseat} from "./CarBackseat.tsx";
import type {CarObject} from "../types/Car.ts";


export function RenderedCar(props){
    const [driver,setDriver]= useState("");
    const [shotgun,setShotgun]= useState("");
    const [rear,setRearender]= useState([]);

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
            if(seatData.name==="free"){
                returnContent =
                    <a onClick={()=>updateCar(props.car,position,false)} title={"Mitfahren"}>
                        {imgString}
                    </a>;
            }else{
                if(seatData.id===String(props.user.id)){
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

    useEffect(()=>{
        setRear();
    },[]);
    let killButton;
    if(props.car.driverId === props.user.id){
      killButton=<button className={"killTheCar"}><img src={"../src/assets/trash.png"} /></button>;
    }else{
        killButton="";
    }

    return (
        <div className={"car"} key={props.car.driverId}>
            {killButton}
            <div className="goTime">
                <b>Abfahrtzeit:</b> {props.car.takeOffTime}
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
                        return <CarBackseat seatData={e} index={index}  user={props.user} car={props.car} setRear={setRear} updateCar={updateCar} seatOutput={seatOutput} />
                    })
                }
            </div>
            <div style={{clear:"both"}} />
        </div>
    )
}