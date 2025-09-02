import type {UserObject} from "../types/User.ts";
import type {TournamentObject} from "../types/Tournament.ts";
import {TournamentCard} from "../components/TournamentCard.tsx";

type turnierProps = {
    user : UserObject;
}

export function TurnierUeberstichPage({user}:turnierProps) {
    let mockTurnierListe:TournamentObject[] = [
        {
            name : "Turnier zu Bochum",
            startDate : "2025-09-29",
            endDate : "2025-09-31",
            description : "Tolles Turnier in Bochum",
            participants : 5,
        },
        {
            name : "Turnier zu Münster",
            startDate : "2025-09-29",
            endDate : "2025-09-31",
            description : "Tolles Turnier in Bochum",
            participants : 5,
        }
    ];

    return (
        <>
            {mockTurnierListe.map((tournament:TournamentObject) => {
                <TournamentCard tournament={tournament} />
            })}
        </>
    );
}