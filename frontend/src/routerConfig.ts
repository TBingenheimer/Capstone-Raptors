type RouterConfig = {
    URL:URL
}

type URL = {
    HOME: string,
    LOGOUT : string,
    TURNIERE : string,
    TURNIER : string,
    TOURNAMENT_CREATE : string,
    PROFILE : string,
}

export const routerConfig:RouterConfig = {
    URL:{
        HOME:"/",
        LOGOUT: "/logout",
        TURNIERE: "/turniere",
        TURNIER : "/turnier",
        TOURNAMENT_CREATE: "/turnier/anlegen",
        PROFILE: "/profile",
    }
}