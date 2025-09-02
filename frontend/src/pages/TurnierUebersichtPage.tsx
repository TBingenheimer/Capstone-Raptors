import type {UserObject} from "../types/User.ts";
import type {TournamentObject} from "../types/Tournament.ts";
import {TournamentCard} from "../components/TournamentCard.tsx";

type turnierProps = {
    user : UserObject;
}

export function TurnierUeberstichPage({user}:turnierProps) {
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
        <div className="tournament-card-wrap">
            {mockTurnierListe.map((tournament)=> {
                return <TournamentCard key={tournament.name} tournament={tournament}  />
            })}
        </div>
    );
}