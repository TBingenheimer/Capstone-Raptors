import {Navigate, Outlet} from "react-router-dom";
import {routerConfig} from "../routerConfig.ts";
import type {User} from "../types/User.ts";

type ProtectedRouteProps = {
    user:User,
}

export function ProtectedRoute({user}: ProtectedRouteProps) {
    //<Navigate to={routerConfig.URL.HOME}/>
    if(user ===  undefined) {
        return 'Läd...';
    }
    return (<Outlet />)
}