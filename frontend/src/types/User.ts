export type UserObject = {
    id: string,
    username: string,
    usernameAlias: string,
    avatar_url: string,
    avatarUrlAlias: string,
}

export type User = UserObject | null | undefined