import type { TournamentObject } from "../types/Tournament.ts";
import "../styles/TournamentDetail.css";
import {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";
import {RenderedCar} from "../components/RenderedCar.tsx";
import type {CarObject} from "../types/Car.ts";

type TournamentDetailPage = {
    tournament: TournamentObject;
};

export function TournamentDetailPage() {
    const [cars, setCars] = useState<CarObject[]>([]);
    const [tournament, setTournamentData] = useState<TournamentObject[]>([]);
    let params = useParams();

    useEffect(() => {
        axios.get("/api/tournament/getTournament/"+params.name)
            .then((response) => {
                    setTournamentData(response.data);
                    axios.get<CarObject[]>("/api/cars/getCars/"+response.data.id)
                        .then((response)=>{
                            setCars(response.data);
                        })
                        .catch((error) => {
                            console.error("Fehler beim Laden der Cars:", error);
                        });
                }
            );
    },[]);
    function addNewCar(){

    }

    return (
        <div className="tournament-detail-wrap contentWrap">
            <button onClick={addNewCar()} id={"newCarButton"}>+ Neues Auto hinzufügen</button>
            <h1 style={{marginBottom:"0"}}>{tournament.name}</h1>
            <p style={{fontSize:"0.8em",marginTop:"0",color:"gray",fontStyle:"italic"}}>{tournament.startDate} - {tournament.endDate}</p>
            <p>{tournament.description}</p>
            {cars.map((car)=>(
                <RenderedCar car={car} />
            ))}

        </div>
    );
}