// Imports
import React from 'react'
import { Route, Redirect } from 'react-router-dom'
import { useSelector } from 'react-redux'

// App Imports
import userRoutes from '../../../scheme/routes/user'

// Component
const RoutePrivate = ({ role, component, ...props }) => {
    const { isAuthenticated, details } = useSelector(state => state.auth)

    return (
        isAuthenticated
            ? role
                ? details.role === role
                    ? <Route {...props} component={component} />
                    : <Redirect to={userRoutes.login.path} />
                : <Route {...props} component={component} />
            : <Redirect to={userRoutes.login.path} />
    )
}

export default RoutePrivate