export type UserObject = {
    id: string,
    gitHubId: string,
    username: string,
    name: string,
    usernameAlias: string,
    avatar_url: string,
    avatarUrlAlias: string,
}

export type User = UserObject | null | undefined