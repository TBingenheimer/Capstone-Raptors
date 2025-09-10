import {Routes, Route} from "react-router-dom";
import {routerConfig} from "../routerConfig.ts";
import {ProtectedRoute} from "./ProtectedRoute.tsx"
import {TournamentOverviewPage} from "./TournamentOverviewPage.tsx";
import {TournamentDetailPage} from "./TournamentDetailPage.tsx";
import {TournamentCreate} from "./TournamentCreate.tsx"
import {HomePage} from "./HomePage.tsx";

import type {UserObject} from "../types/User.ts";
import {UserProfile} from "./UserProfile.tsx";

type RouterProps = {
    user : UserObject;
}

export function Router({user}:RouterProps){
    return (
        <Routes>
            <Route path="/"  element={<HomePage  user={user}/>} />
            <Route path={routerConfig.URL.HOME} element={<HomePage user={user}/>} />
            <Route path={routerConfig.URL.LOGOUT} element={<HomePage user={user}/>}/>
            <Route element={<ProtectedRoute user={user}/>} >
                <Route path={routerConfig.URL.TURNIERE} element={<TournamentOverviewPage user={user}/>}/>
                <Route path={`${routerConfig.URL.TURNIER}/:name`} element={<TournamentDetailPage />}/>
                <Route path={routerConfig.URL.TOURNAMENT_CREATE} element={<TournamentCreate user={user} />} />
                <Route path={routerConfig.URL.PROFILE} element={<UserProfile user={user} />} />
            </Route>
        </Routes>
    );
}