import type { TournamentObject } from "../types/Tournament.ts";
import "../styles/TournamentDetail.css";
import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import {RenderedCar} from "../components/RenderedCar.tsx";
import type {CarObject} from "../types/Car.ts";
import {TrainSeat} from "../components/TrainSeat.tsx";
import type {UserObject} from "../types/User.ts";
import {formatDate} from "../utils/formatDate.ts";
import {MatchResult} from "../components/MatchResult.tsx";

type TournamentDetailPage = {
    tournament: TournamentObject;
};

export function TournamentDetailPage(userObject) {
    const navigate = useNavigate();
    const [cars, setCars] = useState<CarObject[]>([]);
    const [tournament, setTournamentData] = useState<TournamentObject[]>([]);
    const [trainSeats,setTrainSeats] = useState<UserObject[]>([]);
    const [rideTheRails, setRideTheRails] = useState("");
    const [tugenyResults, setTugenyResults] = useState([]);
    const [tugenyRank, setTugenyRank] = useState("");
    let params = useParams();
    const isInThePast = new Date(tournament.startDateTime) < new Date();

    useEffect(() => {
        loadData();
    },[]);
    function loadData(){
        axios.get("/api/tournament/getTournament/"+params.name)
            .then((response) => {
                    setTournamentData(response.data);
                    if(isInThePast){
                        getTugenyData(response.data.tugenyId);
                    }
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
    function getTugenyData(tugenyId){
        axios.get("/api/tournament/getTugenyResults").then((response) => {
            setTugenyResults(response.data.matches.filter((r)=>r.tournament_id==tugenyId));
        });
        axios.get("/api/tournament/getTugenyTournamentRanking/"+tugenyId).then((response) => {
            const rankings=response.data.rankings;
            Object.entries(rankings).forEach((team,rank) => {
                if(team[1].team_id===5){
                    setTugenyRank(team[0]);
                }
            })
        });
    }


    return (
        <div className="tournament-detail-wrap contentWrap">
            {!isInThePast && (<button onClick={addNewCar} id={"newCarButton"}>+ Neues Auto hinzufügen</button>)}
            <h1 style={{marginBottom:"0"}}>{tournament.name}</h1>
            <p>
                <b>Start:</b> {formatDate(tournament.startDateTime)} <br/>
                <b>Ende:</b> {formatDate(tournament.endDateTime)}
            </p>
            <p><b>Adresse:</b><br />
                {tournament.street}, {tournament.zip} {tournament.city}
            </p>
            <p><b>Beschreibung:</b><br />{tournament.description}</p>
            <div id={"placeholder"}>
                <i>Noch keine Autos gemeldet.</i>
            </div>
            <div style={{width:"100%"}}>
            {cars.map((car)=>(
                <RenderedCar car={car} key={car.id} user={userObject.user} loadCars={loadData} tournament={tournament} isInThePast={isInThePast} />
            ))}
                <div style={{clear:"both"}}></div>
            </div>
            <h2 id={"trainHeadline"}>
                Bahnfahrer
                {!isInThePast && (
                    <button onClick={boardDaTrain}>Ich mag Züge!</button>
                )}
            </h2>
            <div id="train">
                {trainSeats.length === 0 ? (
                    <div id="noTrainees">Noch niemand hat sich für die Bahn eingetragen</div>
                ) : (
                    trainSeats.map((seat) => (
                        <TrainSeat
                            key={seat.id}
                            seat={seat}
                            user={userObject.user}
                            loadTrain={loadTrain}
                        />
                    ))
                )}
            </div>
            {isInThePast && (
                <>
                    <h2 style={{marginTop:"60px"}}>Spielergebnisse</h2>
                    <b>Platz: </b>{tugenyRank}<br /><br />
                    <b>Spiele: </b>
                    <div id={"tugenyResults"}>
                        {tugenyResults.map((result) => (
                            <MatchResult result={result} key={result.id} />
                        ))}
                    </div>
                </>
            )}
        </div>
    );
}