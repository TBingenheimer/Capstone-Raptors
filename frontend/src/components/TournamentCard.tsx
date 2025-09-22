import type { TournamentObject } from "../types/Tournament.ts";
import {routerConfig} from "../routerConfig.ts";
import {formatDate} from "../utils/formatDate.ts";

type TournamentCardProps = {
    tournament: TournamentObject,
};

export function TournamentCard(props) {
    console.log(props);
    return (
        <div className="tournament-card">
            <h3>{props.tournament.name}</h3>
            <p>{formatDate(props.tournament.startDateTime)}</p>
            <p>{props.tournament.city}</p>
            <a href={`${routerConfig.URL.TURNIER}/${props.tournament.name}`}>Details</a>
        </div>
    );
}