type RouterConfig = {
    URL:URL
}

type URL = {
    HOME: string,
    LOGOUT : string,
    TURNIERE : string,
    TURNIER : string,
}

export const routerConfig:RouterConfig = {
    URL:{
        HOME:"/",
        LOGOUT: "/logout",
        TURNIERE: "/turniere",
        TURNIER : "/turnier",
    }
}