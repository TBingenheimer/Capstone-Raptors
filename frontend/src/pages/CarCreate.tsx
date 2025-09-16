import type {UserObject} from "../types/User.ts";
import "../styles/CarCreate.css";
import type {CarObject} from "../types/Car.ts";
import axios from "axios";
import {type ChangeEvent, type FormEvent, useEffect, useId, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import type {TournamentObject} from "../types/Tournament.ts";

export function CarCreate(userParam: UserObject) {
    const navigate = useNavigate();
    let params = useParams();
    const [tournament, setTournament] = useState<TournamentObject[]>([]);
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
        const {id,...carWithoutId} = car;
        axios.post("/api/cars/createCar", carWithoutId, {
            headers: {
                "Content-Type": "application/json",
            }
        });
        navigate("/turnier/"+params.name);
    }
    useEffect(() => {
        axios.get("/api/tournament/getTournament/"+params.name)
            .then((response) => {
                car.tournamentId=response.data.id;
                }
            );
    },[]);

    return(<div id={"carCreate"} onSubmit={sendData} className={"contentWrap"}>
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
    </div>);
}