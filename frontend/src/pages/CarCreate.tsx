import type {UserObject} from "../types/User.ts";
import "../styles/CarCreate.css";
import type {CarObject} from "../types/Car.ts";
import axios from "axios";
import {type ChangeEvent, type FormEvent, useEffect, useId, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import type {TournamentObject} from "../types/Tournament.ts";
import {routerConfig} from "../routerConfig.ts";

export function CarCreate(userParam: UserObject) {
    const navigate = useNavigate();
    const [tournament, setTournament] = useState<TournamentObject[]>([]);
    let params = useParams();

    const [car, setCar] = useState<CarObject>({
        id:crypto.randomUUID(),
        driverId:userParam.user.id,
        shotgun:"",
        availableSeats:"0",
        takeOffTime : "",
        tournamentId : tournament.id,
        rear : []
    });

    function setValues(e:ChangeEvent<HTMLInputElement>){
        setCar(prev=>({
            ...prev,
            [e.target.name]: e.target.value
        }));
    }

    function sendData(e:FormEvent<HTMLFormElement>) {
        e.preventDefault();
        let saveCar;
        if(params.carId===undefined){
             const {id,...carWithoutId} = car;
             saveCar = carWithoutId;
        }else{
             saveCar = car;
        }

        axios.post("/api/cars/createCar", saveCar, {
            headers: {
                "Content-Type": "application/json",
            }
        });
        navigate("/turnier/"+params.name);
    }
    useEffect(() => {
        if(params.carId!==undefined) {
            axios.get<CarObject>("/api/cars/getCar/" + params.carId)
                .then((response) => {
                    setCar(response.data);
                });
        }
        axios.get("/api/tournament/getTournament/"+params.name)
            .then((response) => {
                car.tournamentId=response.data.id;
                }
            );
    },[]);

    console.log(car);
    return(<div id={"carCreate"} onSubmit={sendData} className={"contentWrap"}>
        <div id={"carDataForm"}>
            <a href={`${routerConfig.URL.TURNIER}/${params.name}`}>&lt; Zurück</a>
        </div>
        <h1>{params.carId===undefined?"Neues Auto anlegen":"Auto bearbeiten"}</h1>
        <form id={"carCreateForm"}>
            <label>Verfügbare Sitze (exklusive Fahrer): <br />
                <input type={"text"} id={"availableSeats"} name={"availableSeats"} value={car.availableSeats} onChange={setValues} />
            </label>
            <label>Abfahrtzeit:<br />
                <input type={"datetime-local"} name={"takeOffTime"} value={car.takeOffTime}  onChange={setValues}/>
            </label>
            <input type={"submit"} id={"carSubmit"} value={"Speichern"} />
        </form>
    </div>);
}