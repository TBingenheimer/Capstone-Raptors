export type TournamentObject = {
    name : string,
    startDate : string,
    endDate : string,
    description : string,
    participants : number,
}

export type Tournament = TournamentObject | null | undefined