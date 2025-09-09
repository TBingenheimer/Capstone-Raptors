import type {UserObject} from "../types/User.ts";
import type {TournamentObject} from "../types/Tournament.ts";
import {TournamentCard} from "../components/TournamentCard.tsx";
import "../styles/TournamentOverview.css";
import axios from "axios";
import {useEffect, useState} from "react";
import {map} from "react-bootstrap/ElementChildren";

type turnierProps = {
    user : UserObject;
}

export function TournamentOverviewPage({user}:turnierProps) {
    const [tournaments, setTournaments] = useState<TournamentObject[]>([]);
    useEffect(() => {
        axios.get("/api/tournament/getAllTournaments")
            .then((response)=>{
                setTournaments(response.data.tournaments);
            });
    },[])
    console.log(tournaments);
    let mockTurnierListe = [
        {
            name : "Turnier zu Bochum",
            startDate : "2025-09-29",
            endDate : "2025-09-31",
            description : "Tolles Turnier in BOCHUM",
            participants : 5,
        },
        {
            name : "Turnier zu Münster",
            startDate : "2025-07-25",
            endDate : "2025-07-27",
            description : "Tolles Turnier in MÜNSTER",
            participants : 7,
        }
    ];

    return (
        <div className="tournament-card-wrap contentWrap">
            <link href={"../src/styles/TournamentOverview.css"} rel="stylesheet" />
            <h1 style={{width:"100%"}}>Offene Turniere</h1>
            {tournaments.map((tournament:TournamentObject)=> {
                return <TournamentCard key={tournament.name} tournament={tournament}  />
            })}
        </div>
    );
}