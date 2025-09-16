import {useEffect, useState} from "react";
import {CarObject} from "../types/Car.js";

export function carData(props: any) {
    const[car,setCar]=useState<CarObject>({
        id:crypto.randomUUID(),
        driverId:"0",
        shotgun:"0",
        availableSeats:"5",
        takeOffTime:"",
        tournamentId:"",
        rear:[]
    });
    useEffect(()=>{
        if(props.data.id!==undefined){
            setCar(props.data.carId);
        }
    },[]);


    return (
        <div id={"carCreate"} onSubmit={sendData} className={"contentWrap"}>
            <h1>Neues Auto anlegen</h1>
            <form id={"carCreateForm"}>
                <label>Verfügbare Sitze (exklusive Fahrer): <br />
                    <input type={"text"} id={"availableSeats"} name={"availableSeats"} value={car.availableSeats} onChange={setValues} />
                </label>
                <label>Abfahrtzeit:<br />
                    <input type={"datetime-local"} name={"takeOffTime"} value={car.takeOffTime}  onChange={setValues}/>
                </label>
                <input type={"submit"} id={"carSubmit"} value={"Speichern"} />
            </form>
        </div>
    )
}