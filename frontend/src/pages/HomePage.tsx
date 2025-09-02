import type {UserObject} from "../types/User.ts";

type homeProps = {
    user : UserObject;
}

export function HomePage({user}:homeProps) {
    let output;
    if(user === undefined){
        output = <h1 className={"center"}>Leider musst du dich einloggen um hier mehr zu sehen.</h1>;
    }else{
        output = <h1>Raptorparty!</h1>;
    }

    return (
        <>
            {output}
        </>
    )
}