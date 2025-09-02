import type {TournamentObject} from "../types/Tournament.ts";

type TournamentProps = {
    tournament : UserObject;
}

export function TournamentCard({tournament}:TournamentProps) {
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
            {mockTurnierListe.map((turnier:TournamentObject) => {
                <TournamentCard  />
            })}
        </>
    );
}