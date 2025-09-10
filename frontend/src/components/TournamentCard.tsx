import type { TournamentObject } from "../types/Tournament.ts";
import {routerConfig} from "../routerConfig.ts";

type TournamentCardProps = {
    tournament: TournamentObject;
};

export function TournamentCard({ tournament }: TournamentCardProps) {
    return (
        <div className="tournament-card">
            <h3>{tournament.name}</h3>
            <p>{tournament.startDate}</p>
            <a href={`${routerConfig.URL.TURNIER}/${tournament.name}`}>Details</a>
        </div>
    );
}