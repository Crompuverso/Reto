export class LoginDTO {
    private _password!: string;
    private _username!: string;

    public get password(): string {
        return this._password
    }
    public get username(): string {
        return this._username
    }

    public set password(_password: string) {
        this._password = _password;
    }
    public set username(_username: string) {
        this._username = _username;
    }
}