function [ib,b,sigma] = genererimage(i, SNR)
    % calcul de Pi
    Pi = sum(sum(i.^2)) / (size(i,1)*size(i,2));
    
    % calcul de Pb 
    Pb = Pi ./ (10^(SNR/10));
    sigma  =  sqrt(Pb);
    b = sigma * randn(size(i));
    ib = i + b; 
end