function Q_next = etat(Q_cur, q_com, Te)
    v = q_com(1);
    w = q_com(2);
    w_pl = q_com(3);
    theta = Q_cur(3);
    q_pl = Q_cur(4);
    
    %Ici x. et y. dépendent de theta+w au lieu de juste theta comme indiqué
    %sur le sujet, ce qui est plus cohérent car on prend en compte la
    %rotation qui vient de lui être appliquée et pas que celle qu'il a déjà
    %en se servant que de theta le robot atteint sa cible mais tourne en
    %rond sans cesse et met plus longtemps
    Q_dot = [v*cos(theta+w); v*sin(theta+w); w; w_pl];
    Q_next = Te * Q_dot + Q_cur;
end