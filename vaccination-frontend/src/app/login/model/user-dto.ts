export class UserDTO {
    private _dni!: string;
    private _email!: string;
    private _name!: string;
    private _roles!: string[];
    private _surname!: string;

    public get dni(): string {
        return this._dni;
    }
    public get email(): string {
        return this._email;
    }
    public get name(): string {
        return this._name;
    }
    public get roles(): string[] {
        return this._roles;
    }
    public get surname(): string {
        return this._surname;
    }

    public set dni(_dni: string) {
        this._dni = _dni;
    }
    public set email(_email: string) {
        this._email = _email;
    }
    public set name(_name: string) {
        this._name = _name;
    }
    public set roles(_roles: string[]) {
        this._roles = _roles;
    }
    public set surname(_surname: string) {
        this._surname = _surname;
    }
}