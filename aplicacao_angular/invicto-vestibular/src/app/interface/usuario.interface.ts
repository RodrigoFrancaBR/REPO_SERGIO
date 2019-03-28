import { TokenUsuario } from './../model/token.usuario';
// tslint:disable-next-line:no-empty-interface
export interface UsuarioInterface {

    iss: string;
    iat: number;
    TokenUsuario: TokenUsuario;
}
