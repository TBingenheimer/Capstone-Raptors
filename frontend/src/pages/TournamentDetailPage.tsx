import type { TournamentObject } from "../types/Tournament.ts";
import "../styles/TournamentDetail.css";
import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import {RenderedCar} from "../components/RenderedCar.tsx";
import type {CarObject} from "../types/Car.ts";

type TournamentDetailPage = {
    tournament: TournamentObject;
};

export function TournamentDetailPage(userObject) {
    const navigate = useNavigate();
    const [cars, setCars] = useState<CarObject[]>([]);
    const [tournament, setTournamentData] = useState<TournamentObject[]>([]);
    let params = useParams();

    useEffect(() => {
        loadCars();
    },[]);
    function loadCars(){
        axios.get("/api/tournament/getTournament/"+params.name)
            .then((response) => {
                    setTournamentData(response.data);
                    axios.get<CarObject[]>("/api/cars/getCars/"+response.data.id)
                        .then((response)=>{
                            if(response.data.length>0){
                                setCars(response.data);
                                document.querySelector("#placeholder").style.display="none";
                            }
                        })
                        .catch((error) => {
                            console.error("Fehler beim Laden der Cars:", error);
                        });
                }
            );
    }
    function addNewCar(){
        navigate("/turnier/"+params.name+"/add");
    }

    return (
        <div className="tournament-detail-wrap contentWrap">
            <button onClick={addNewCar} id={"newCarButton"}>+ Neues Auto hinzufügen</button>
            <h1 style={{marginBottom:"0"}}>{tournament.name}</h1>
            <p style={{fontSize:"0.8em",marginTop:"0",color:"gray",fontStyle:"italic"}}>{tournament.startDateTime} - {tournament.endDateTime}</p>
            <p><b>Adresse:</b><br />
                {tournament.street}, {tournament.zip} {tournament.city}
            </p>
            <p><b>Beschreibung:</b><br />{tournament.description}</p>
            <div id={"placeholder"}>
                <i>Noch keine Autos gemeldet.</i>
            </div>
            {cars.map((car)=>(
                <RenderedCar car={car} user={userObject.user} loadCars={loadCars} />
            ))}

        </div>
    );
}