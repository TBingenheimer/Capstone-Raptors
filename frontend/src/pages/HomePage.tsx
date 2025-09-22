import type {UserObject} from "../types/User.ts";
import {TournamentOverviewPage} from "./TournamentOverviewPage.tsx";

type homeProps = {
    user : UserObject;
    subpage : string;
}

export function HomePage({user, subpage}:homeProps) {
    let output;
    if(user === undefined){
        return <div style={{textAlign:"center"}}><h1 className={"center"}>Leider musst du dich einloggen um hier mehr zu sehen.</h1> <img src={"../src/assets/COLOGNE-RAPTORS-LOGO.png"} style={{width:"500px"}} /></div>;
    }

    output = <h1>Raptorparty!</h1>;


    return (
        <>
            <TournamentOverviewPage user={user} subpage={subpage} />
        </>
    )
}