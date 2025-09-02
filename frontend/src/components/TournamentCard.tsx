import type {TournamentObject} from "../types/Tournament.ts";

type TournamentProps = {
    tournament : TournamentObject;
}

export function TournamentCard({tournament}:TournamentProps) {


    return (
        <>
            {tournament.name}
        </>
    );
}