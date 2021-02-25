const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const Dotenv = require('dotenv-webpack');

module.exports = {
    entry: {
        index: './src/index.js',
    },
    devtool: 'inline-source-map',
    devServer: {
        contentBase: './public',
        hot: true,
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /(node_modules|bower_components)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env', '@babel/preset-react'],
                        plugins: ['@babel/plugin-proposal-object-rest-spread']
                    }
                }
            }
        ],
    },
    plugins: [

        new CleanWebpackPlugin(),
        new HtmlWebpackPlugin({
            template: "public/index.html",

        }),
        new Dotenv(),
    ],
    resolve: {
        extensions: ['.js', '.jsx'],
    },

    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'public', 'js'),
    },

};
