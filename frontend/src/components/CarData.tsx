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
        <>
        <form>
            <label>Anzahl verfügbarer Sitze:<br />
                <input type="number" name={"availableSeats"} onChange={"setCar"} value={car.availableSeats} />
            </label>
            <label>Anzahl verfügbarer Sitze:<br />
                <input type="datetime-local" name={"takeOffTime"} onChange={"setCar"} value={car.takeOffTime} />
            </label>
            <input type={"submit"} value={"Speichern"} />
        </form>
        </>
    )
}