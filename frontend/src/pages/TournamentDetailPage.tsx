import type { TournamentObject } from "../types/Tournament.ts";
import "../styles/TournamentDetail.css";
import {useState} from "react";

type TournamentDetailPage = {
    tournament: TournamentObject;
};

export function TournamentDetailPage() {
    let tournament = {
        name : "Turnier zu Bochum",
        startDate : "2025-09-29",
        endDate : "2025-09-31",
        description : "Tolles Turnier in BOCHUM",
        currParticipants : 5,
        participants : [1,2,3,5,6]

    };
    console.log(tournament);
    let cars = [
        {
            driver : 1,
            seats : 4,
            startTime : "2025-09-29 17:00:00",
            riders : [3,4,2]
        },
        {
            driver:5,
            seats:5,
            startTime : "2025-09-29 15:00:00",
            riders : [7,8]
        }
    ];

    let tcarString;
    cars.forEach(car => {
        console.log(car.riders);
        tcarString += '<div class="car">';
        for (let j = 0; j < car.seats; j++) {
            tcarString += `<div style="border:1px solid black;">${car.riders[j]}</div>`;
        }
        tcarString += '</div>';
    });
    const [carsString, setCarsString] = useState(tcarString);


    return (
        <div className="tournament-detail-wrap">
            <h1 style={{marginBottom:"0"}}>{tournament.name}</h1>
            <p style={{fontSize:"0.8em",marginTop:"0",color:"gray",fontStyle:"italic"}}>{tournament.startDate} - {tournament.endDate}</p>
            <p>{tournament.description}</p>
            {carsString}
        </div>
    );
}