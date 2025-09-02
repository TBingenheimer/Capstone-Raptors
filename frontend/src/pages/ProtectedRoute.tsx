import {Navigate, Outlet} from "react-router-dom";
import {routerConfig} from "../routerConfig.ts";
import type {User} from "../types/User.ts";

type ProtectedRouteProps = {
    user:User,
}

export function ProtectedRoute({user}: ProtectedRouteProps) {
    if(user ===  undefined) {
        return <Navigate to={routerConfig.URL.HOME}/>
    }

    return (<Outlet />)
}