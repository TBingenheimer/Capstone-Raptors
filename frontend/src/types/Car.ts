export type CarObject = {
    id: string,
    driverId: string,
    shotgun: string,
    availableSeats : string,
    takeOffTime: string,
    tournamentId:string,
    rear: string[],
}

export type User = CarObject | null | undefined