// Imports
import React from 'react'
import PropTypes from 'prop-types'
import { Link } from 'react-router-dom'

// UI Imports
import Toolbar from '@material-ui/core/Toolbar/Toolbar'
import Button from '@material-ui/core/Button/Button'
import Typography from '@material-ui/core/Typography/Typography'
import { withStyles } from '@material-ui/core/styles/index'
import styles from './styles'

// App Imports
import routes from '../../../../scheme/routes'
import AuthCheck from '../../auth/AuthCheck'
import Section from '../../base/Section'

// Component
const Home = ({ classes }) => (
    <div>
        <Toolbar>
            <Typography variant="h6" color="inherit" className={classes.grow}>
                Home
      </Typography>
        </Toolbar>

        <Section>
            <Typography paragraph>Simple RBAC application!</Typography>

            <Link to={routes.signup.path}>
                <Button variant="contained" color="primary">Signup</Button>
            </Link>

            <Link to={routes.login.path}>
                <Button variant="outlined" color="primary" style={{ marginLeft: 10 }}>Login</Button>
            </Link>
        </Section>

        {/* Auth Check */}
        <AuthCheck />
    </div>
)

// Component Properties
Home.propTypes = {
    classes: PropTypes.object.isRequired
}

export default withStyles(styles)(Home)