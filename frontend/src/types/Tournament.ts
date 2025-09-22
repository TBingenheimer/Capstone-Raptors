export type TournamentObject = {
    id:string,
    name : string,
    description : string,
    street : string,
    zip : string,
    city : string,
    startDateTime : string,
    endDateTime : string,
    participants : number,
    tugenyId : string,
}

export type Tournament = TournamentObject