import './styles/style.css'
import axios from "axios";
import {useEffect, useState} from "react";
import type {UserObject} from "./types/User.ts";

import {Router} from "./pages/Router.tsx";
import {Navbar} from "./components/Navbar.tsx";


function App() {
    const [user, setUser] = useState<UserObject>(undefined);

    const loadUser = () => {
        axios.get("/api/auth")
            .then((response) => {
                let userData = response.data.attributes;
                axios.get("/api/user/getuserByGithubId/"+userData.id).then(
                    (response) => {
                        userData.gitHubId = userData.id;
                        userData.id = response.data;
                        setUser(userData);
                    }
                );
            });
    }
    const login = (event)=> {
        event.preventDefault();
        const host:string = window.location.host === "localhost:5173" ? "http://localhost:8080" : window.location.origin;
        window.open(host + "/oauth2/authorization/github", "_self")
    }
    const logout = (event) =>{
        event.preventDefault();
        const host:string = window.location.host === "localhost:5173" ? "http://localhost:8080" : window.location.origin;
        window.open(host + "/logout","_self");
    }
    useEffect(()=>{
        loadUser();
    },[]);

  return (
    <>
      <header>
        <Navbar user={user} logout={logout} login={login} />
      </header>
      <Router user={user} />
    </>
  )
}

export default App
