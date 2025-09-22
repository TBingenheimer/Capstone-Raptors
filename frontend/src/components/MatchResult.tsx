export function MatchResult(props){;
    let className;
    if(props.result.victorious_team_id==="5"){
        className="green";
    }else{
        className = "red";
    }

    return (
        <div class={"tugenyResult " + className}>
            {props.result.first_team} : {props.result.second_team}<br /><br />
            {props.result.score_total}
        </div>
    );
}