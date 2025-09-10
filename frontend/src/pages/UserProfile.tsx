import type {UserObject} from "../types/User.ts";
import "../styles/UserProfile.css";
import {useEffect, useState} from "react";

export function UserProfile({user}:UserObject){
    const [userData, setUser] = useState<UserObject>();

    useEffect(() => {
        setUser(user);
    },[])
    console.log(userData);
    function saveUsername(e){
        e.preventDefault();
    }

    return (
        <div id={"userProfile"} className={"contentWrap"}>
            <div>
                <h2>Avatar</h2>
                <img src={userData?.avatar_url} id={"avatar"} alt={"Avatar"} />
            </div>
            <form onSubmit={saveUsername} id="userNameForm">
                <label>
                    <h2>Anzeigename:</h2>
                    <input type={"text"} name={"username"} value={userData?.name} />
                </label>
                <input type={"submit"} value={"Speichern"} />
            </form>
        </div>
    )
}