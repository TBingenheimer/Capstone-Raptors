export type UserObject = {
    id: string,
    username: string,
    usernameAlias: string,
    avatarUrl: string,
    avatarUrlAlias: string,
}

export type User = UserObj | null | undefined