import type {UserObject} from "../types/User.ts";
import "../styles/TournamentCreate.css";
import type {Tournament} from "../types/Tournament.ts";
import axios from "axios";
import {type ChangeEvent, type FormEvent, useEffect, useId, useState} from "react";
import {useNavigate} from "react-router-dom";

export function TournamentCreate(user: UserObject) {
    const navigate = useNavigate();
    const [tournament, setTournament] = useState<Tournament>({
        id:crypto.randomUUID(),
        name:"",
        description:"",
        street:"",
        zip:"",
        city:"",
        startDateTime:"",
        endDateTime:"",
        participants:0
    });

    function setValues(e:ChangeEvent<HTMLInputElement>){
        setTournament(prev=>({
            ...prev,
            [e.target.name]: e.target.value
        }));
    }

    function sendData(e:FormEvent<HTMLFormElement>) {
        e.preventDefault();
        const {id,...tournamentWithoutId} = tournament;
        axios.post("/api/tournament/createTournament", tournamentWithoutId, {
            headers: {
                "Content-Type": "application/json",
            }
        }).then(() => {
            navigate("/");
        });

    }

    return(<div id={"tournamentCreate"} onSubmit={sendData} className={"contentWrap"}>
        <h1>Neues Turnier anlegen</h1>
        <form id={"tournamentCreateForm"}>
            <label>Turniername: <br />
                <input type={"text"} id={"name"} name={"name"} value={tournament.name} onChange={setValues} />
            </label>
            <label>Strasse:<br />
                <input type={"text"} name={"street"} value={tournament.street}  onChange={setValues}/>
            </label>
            <label>PLZ:<br />
                <input type={"text"} name={"zip"} value={tournament.zip}  onChange={setValues}/>
            </label>
            <label>Ort:<br />
                <input type={"text"} name={"city"} value={tournament.city}  onChange={setValues}/>
            </label>
            <label>Start: <br />
                <input type={"datetime-local"} id={"tournamentStartTime"} name={"startDateTime"} value={tournament.startDate}  onChange={setValues}/>
            </label>
            <label>Ende: <br />
                <input type={"datetime-local"} id={"tournamentEndTime"} name={"endDateTime"} value={tournament.endDate}  onChange={setValues}/>
            </label>
            <label>Beschreibung: <br />
                <textarea id={"tournamentDescription"} name={"description"}  onChange={setValues} value={tournament.description} />
            </label>
            <input type={"submit"} id={"tournamentSubmit"} value={"Speichern"} />
        </form>
    </div>);
}