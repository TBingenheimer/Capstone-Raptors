export type TournamentObject = {
    id:string,
    name : string,
    startDate : string,
    endDate : string,
    description : string,
    participants : number,
}

export type Tournament = TournamentObject | null | undefined