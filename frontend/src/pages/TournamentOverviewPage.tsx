import type {UserObject} from "../types/User.ts";
import type {TournamentObject} from "../types/Tournament.ts";
import {TournamentCard} from "../components/TournamentCard.tsx";
import "../styles/TournamentOverview.css";
import axios from "axios";
import {useEffect, useState} from "react";


type turnierProps = {
    user : UserObject;
    subpage : string;
}

export function TournamentOverviewPage({user, subpage}:turnierProps) {
    const [openTournaments, setOpenTournaments] = useState<TournamentObject[]>([]);
    const [pastTournaments, setPastTournaments] = useState<TournamentObject[]>([]);
    useEffect(() => {

        axios.get("/api/tournament/getAllTournaments")
            .then((response)=>{
                const openTournaments = response.data.tournaments.filter((r)=>new Date(r.startDateTime) >= new Date())
                    .sort((a, b) => new Date(a.startDateTime) - new Date(b.startDateTime));
                const pastTournaments = response.data.tournaments.filter((r)=>new Date(r.startDateTime) < new Date())
                    .sort((a, b) => new Date(a.startDateTime) - new Date(b.startDateTime));

                setOpenTournaments(openTournaments);
                setPastTournaments(pastTournaments);
            });
    },[]);

    console.log(subpage);

    return (
        <div className="tournament-card-wrap contentWrap">
            <link href={"../src/styles/TournamentOverview.css"} rel="stylesheet" />
            {(subpage === "open" || subpage === "all") && (
                <>
                    <h1 style={{width:"100%"}}>Offene Turniere</h1>
                    {openTournaments.map((tournament:TournamentObject)=> {
                        return <TournamentCard key={tournament.name} tournament={tournament} />
                    })}
                </>
            )}

            <div style={{clear:"both"}}></div>
            {subpage !== "open" && (
                <>
                    <h1 style={{width:"100%"}}>Abgeschlossene Turniere</h1>
                    {pastTournaments.map((tournament:TournamentObject)=> {
                        return <TournamentCard key={tournament.name} tournament={tournament} />
                    })}
                </>
            )}
        </div>
    );
}