export type TournamentObject = {
    id:string,
    name : string,
    description : string,
    street : string,
    zip : string,
    city : string,
    startDate : string,
    endDate : string,
    participants : number,
}

export type Tournament = TournamentObject | null | undefined