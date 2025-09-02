type RouterConfig = {
    URL:URL
}

type URL = {
    HOME: string,
    LOGOUT : string,
    TURNIERE : string,
    TURNIER : (id:string)=>string,
}

export const routerConfig:RouterConfig = {
    URL:{
        HOME:"/",
        LOGOUT: "/logout",
        TURNIERE: "/turniere",
        TURNIER:(id)=> `/turnier/${id}`,
    }
}