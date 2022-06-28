function [Q_fin, atteint, iters, traj] = goto_cible(Q_init, s_star, OPr, maxiter, eps, Te)
    i = 0;
    s = s_star;
    err = eps + 1;
    Q_cur = Q_init;
    
    drawRobotPlatine(Q_init(1), Q_init(2), Q_init(3), Q_init(4));
    traj = [Q_init(1), Q_init(2)];
    
    while(any(err > eps) && i < maxiter)
        %Calcul des coordonées actuelles des points
        [CP1c, CP2c, CP3c, CP4c] = visu(Q_cur, OPr);
        z = [CP1c(3); CP2c(3); CP3c(3); CP4c(3)];

        %Indices visuels actuels et erreur avec s_star
        s = [CP1c(1:2); CP2c(1:2); CP3c(1:2); CP4c(1:2)];
        err = s - s_star;

        %Affichage des points dans l'image
        %drawImage(CP1c(1),CP1c(2),CP2c(1),CP2c(2),CP3c(1),CP3c(2),CP4c(1),CP4c(2));

        %Calcul de la commande à réaliser pour minimiser l'erreur entre s et s_star
        q_com = commande(s, s_star, 0, z);

        %Calcul de la situation après avoir réalisé la commande précédente
        Q_next = etat(Q_cur, q_com, Te)
        
        %Afficher l'état courant du robot et garder sa trajectoire
        if(mod(i, 500) == 0)
            drawRobotPlatine(Q_next(1), Q_next(2), Q_next(3), Q_next(4));
        end
        traj = [traj; Q_next(1) Q_next(2)];
        
        %Itération suivante
        Q_cur = Q_next;
        i = i + 1;
    end
    
    Q_fin = Q_cur;
    drawRobotPlatine(Q_fin(1), Q_fin(2), Q_fin(3), Q_fin(4))
    traj = [traj; Q_fin(1) Q_fin(2)];
    atteint = i < maxiter;
    iters = i;
end