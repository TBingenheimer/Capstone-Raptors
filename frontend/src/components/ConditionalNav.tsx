import type {UserObject} from "../types/User.ts";

export function ConditionalNav({user}:UserObject){
    if(user!==undefined){
        return(
            <>
                <li>
                    <a href={"/turniere"}>Offene Turniere</a>
                </li>
                <li>
                    <a href={"/turnier/anlegen"}>Neues Turnier</a>
                </li></>
        );
    }else{
        return "";
    }

}