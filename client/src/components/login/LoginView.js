import Alert from "react-bootstrap/Alert";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import {Fragment, useState} from "react";
import Menu from "../Menu";
import LoginForm from "./LoginForm";

function LoginView() {
    const [loginError, setLoginError] = useState(false);
    const loginAlert = (
        <Alert variant="danger" onClose={() => setLoginError(false)} dismissible>
            <Alert.Heading>Błąd logowania</Alert.Heading>
            Podczas logowania wystąpił nieoczekiwany błąd. Spróbuj ponownie.
        </Alert>
    );

    return (
        <Fragment>
            <Menu isLogged={true} canRead={true} canAdd={true}/>
            {loginError ? loginAlert : null}
            <Container>
                <Row className="justify-content-center">
                    <LoginForm setLoginError={(value) => setLoginError(value)}/>
                </Row>
            </Container>
        </Fragment>
    );
}

export default LoginView;