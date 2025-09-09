import type {UserObject} from "../types/User.ts";
import "../styles/TournamentCreate.css";
import axios from "axios";

export function TournamentCreate(user: UserObject) {
    function sendData(e) {
        e.preventDefault();
        console.log("Cliggy");
        //axios.put("", JSON.stringify(document.querySelector("#tournamentCreate")));
    }
    return(<div id={"tournamentCreate"} onSubmit={sendData} className={"contentWrap"}>
        <h1>Neues Turnier anlegen</h1>
        <form id={"tournamentCreateForm"}>
            <label>Turniername: <br />
                <input type={"text"} id={"tournamentName"} />
            </label>
            <label>Start: <br />
                <input type={"text"} id={"tournamentName"} />
            </label>
            <label>Ende: <br />
                <input type={"text"} id={"tournamentName"} />
            </label>
            <label>Beschreibung: <br />
                <textarea id={"tournamentBeschreibung"} />
            </label>
            <input type={"submit"} id={"tournamentSubmit"} value={"Speichern"} />
        </form>
    </div>);
}