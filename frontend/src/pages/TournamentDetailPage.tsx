import type { TournamentObject } from "../types/Tournament.ts";
import "../styles/TournamentDetail.css";
import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import {RenderedCar} from "../components/RenderedCar.tsx";
import type {CarObject} from "../types/Car.ts";
import {TrainSeat} from "../components/TrainSeat.tsx";
import type {UserObject} from "../types/User.ts";

type TournamentDetailPage = {
    tournament: TournamentObject;
};

export function TournamentDetailPage(userObject) {
    const navigate = useNavigate();
    const [cars, setCars] = useState<CarObject[]>([]);
    const [tournament, setTournamentData] = useState<TournamentObject[]>([]);
    const [trainSeats,setTrainSeats] = useState<UserObject[]>([]);
    const [rideTheRails, setRideTheRails] = useState("");
    let params = useParams();

    useEffect(() => {
        loadCars();
    },[]);
    function loadCars(){
        axios.get("/api/tournament/getTournament/"+params.name)
            .then((response) => {
                    setTournamentData(response.data);
                    loadTrain(response.data.id)
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
    async function loadTrain(tournamentId:String){
        try {
            // 1. Cars/Teilnahmen laden
            const trainRes = await axios.get("/api/train/getTrain/"+tournamentId);
            if(trainRes.status === 200){
                document.querySelector("#noTrainees").style.display="none";
                const train = trainRes.data;


                // 2. Alle User laden
                const usersRes = await axios.get("/api/user/getAllUsers");
                const users = usersRes.data;
                let userInTrain=false;
                // 3. Zusammenführen
                const merged = train.map((seat) => {
                    if(seat.userId == userObject.user.id){
                        userInTrain = true;
                    }
                    const user = users.find((u) => u.id === seat.userId);
                    return {
                        id: seat.id,
                        tournamentId: seat.tournamentId,
                        userId: seat.userId,
                        avatar_url: user ? user.avatar_url : null,
                    };
                });
                if(userInTrain){
                    document.querySelector("#trainHeadline button").style.display="none";
                }else{
                    document.querySelector("#trainHeadline button").style.display="inline-block";
                }

                setTrainSeats(merged);
            }
        } catch (err) {
            console.error("Fehler beim Laden:", err);
        }
    }
    function boardDaTrain(){
        axios.post("/api/train/rideTrain",{
                tournamentId : tournament.id,
                userId : userObject.user.id,
            },
            {
                headers: {
                    "Content-Type": "application/json",
                }
            }).then(()=>loadTrain(tournament.id));
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
            <div style={{width:"100%"}}>
            {cars.map((car)=>(
                <RenderedCar car={car} user={userObject.user} loadCars={loadCars} />
            ))}
                <div style={{clear:"both"}}></div>
            </div>
            <h2 id={"trainHeadline"}>
                Bahnfahrer
                <button onClick={boardDaTrain}>Ich mag Züge!</button>
            </h2>
            <div id={"train"}>
                <div id={"noTrainees"}>Noch niemand hat sich für die Bahn eingetragen</div>
                {trainSeats.map((seat)=>(
                    <TrainSeat seat={seat} user={userObject.user} loadTrain={loadTrain} />
                ))}
            </div>
        </div>
    );
}