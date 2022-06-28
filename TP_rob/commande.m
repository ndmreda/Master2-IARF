function q_com = commande(s,s_desire, qpl, z)
    lambda =0.25;
    Dx = 0.1;
    a = 0;
    b = 0;
    c = 0;
    
    %Calcul de la jacobienne
    J_red = [-sin(qpl) a+Dx*cos(qpl) a;
             cos(qpl) -b*Dx*sin(qpl) -b;
             0          -1           -1];

    %Calcul de la matrice L
    L_total = [];
    for i = (0:3)
        L_redi = [0         s(i*2+1)/z(i+1) s(i*2+1)*s(i*2+2);
                  -1/z(i+1) s(i*2+2)/z(i+1) 1+s(i*2+2)^2];
        L_total = [L_total; L_redi];
    end
    
    %Calcul de la matrice A
    A = L_total*J_red;
    
    %Calcul du vecteur b
    erreur = s - s_desire; 
    B = -lambda*erreur;
    
    %Calcul de la commande q. à envoyer 
    %On la dermine par moindres carrés (A . q* = b)
    q_com = inv((A' * A)) * A' * B;
end