import type {UserObject} from "../types/User.ts";
import {routerConfig} from "../routerConfig.ts";
import {ConditionalNav} from "../components/ConditionalNav.tsx";

type NavbarProps = {
    user : UserObject;
    login: () => void;
    logout: ()=>void;
}

export function Navbar({user,login,logout}:NavbarProps) {
    let returnContent;
    if(user===undefined){
        returnContent = <button onClick={login} id={"loginbutton"}>Login</button>;
    }else{
        returnContent =
            <>
                <img src={user.avatar_url} id={"userMenuAvatar"} />
                <div id={"hiddenMenu"}>
                    <ul>
                        <li>
                            <a href={routerConfig.URL.TOURNAMENT_CREATE}>Neues Turnier</a>
                        </li>
                        <li>
                            <a href={routerConfig.URL.PROFILE}>Profil</a>
                        </li>

                    </ul>

                    <button onClick={logout}>Logout</button>
                </div>
            </>;
    }


    return (
        <nav>
            <a href={"/"}>
                <img src={"../src/assets/150xRaptors_contour.png"} id={"navIcon"} />
            </a>
            <ul id={"navMenu"}>
                <li>
                    <a href={"/"}>Home</a>
                </li>
                <ConditionalNav user={user} />
            </ul>
            <div id={"userMenu"}>
            {returnContent}
            </div>
        </nav>
    )
}