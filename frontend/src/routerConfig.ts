type RouterConfig = {
    URL:URL
}

type URL = {
    HOME: string,
    LOGOUT : string,
    TURNIERE : string,
}

export const routerConfig:RouterConfig = {
    URL:{
        HOME:"/",
        LOGOUT: "/logout",
        TURNIERE: "/turniere",
    }
}