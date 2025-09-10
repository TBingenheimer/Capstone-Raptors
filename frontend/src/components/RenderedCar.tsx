import {useEffect, useState} from "react";
import axios from "axios";

export function RenderedCar(props){
    const [driver,setDriver]= useState("");
    const [shotgun,setShotgun]= useState("");
    const [rear,setRearender]= useState([]);
    //console.log("Driver id: ",props.car.driverId);

    useEffect(()=>{
       axios.get("/api/user/getAllUsers")
           .then((response)=>{
                response.data.map((e)=>{
                    const users = response.data;

                    const driverUser = users.find((u) => u.id == props.car.driverId);
                    if (driverUser) setDriver(driverUser);

                    const shotgunUser = users.find((u) => u.id == props.car.shotgun);
                    if (shotgunUser) {
                        setShotgun(shotgunUser);
                    }else{
                        setShotgun({"id":"","avatar_url":"http://localhost:5173/src/assets/free.png"});
                    }

                    let seatsLeft=props.car.availableSeats - (shotgunUser!==undefined?1:0);
                    const rearUsers = users.filter((u) => props.car.rear.includes(u.id));
                    if(rearUsers.length<seatsLeft){
                        for(let i=rearUsers.length;i<seatsLeft;i++){
                            rearUsers[i] = {"id":"","avatar_url":"http://localhost:5173/src/assets/free.png","name":"free"}
                        }
                    }
                    setRearender(rearUsers);
                });

           });
       axios.get("/api/user/getUser/0")
           .then((response)=>{
               console.log(response);
           })
    },[]);
    let nonoImage = "http://localhost:5173/src/assets/nono.png";

    return (
        <div className={"car"} key={props.car.driverId}>
            <div className="goTime">
                <b>Abfahrtzeit:</b> {props.car.takeOffTime}
            </div>
            <div className={"frontRow"}>
                <div className={"rider driver"}>
                    <img src={driver.avatar_url} alt="" />
                </div>
                <div className={"rider shotgun"}>
                    {((shotgun?.avatar_url!==undefined)?<img src={shotgun?.avatar_url} />:'')}
                </div>
            </div>
            <div className={"backRow"}>
                <div className={"rider left"}>
                    {((rear[0]?.avatar_url!==undefined)?<img src={rear[0]?.avatar_url} />:<img src={nonoImage} />)}
                </div>
                <div className={"rider middle"}>
                    {((rear[1]?.avatar_url!==undefined)?<img src={rear[1]?.avatar_url} />:<img src={nonoImage} />)}
                </div>
                <div className={"rider right"}>
                    {((rear[2]?.avatar_url!==undefined)?<img src={rear[2]?.avatar_url} />:<img src={nonoImage} />)}
                </div>
            </div>
            <div style={{clear:"both"}} />
        </div>
    )
}