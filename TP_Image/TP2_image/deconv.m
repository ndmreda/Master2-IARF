clear all; % clear all variables
close all; % close all figures
clc;       % clear command window

x = round(5*rand(3,3));
h = [1 4 7 ; 2 5 8 ; 3 6 9];
%a
Cov = conv2(x,h);
%b
fft_x = fft2(x);
fft_h = fft2(h);

fft_Cov = conv2(fft_x,fft_h);
fft_Cov = real(fft_Cov);

%c
y = imfilter(h,x,'conv','same','circular');
%d
[Mh, Nh] = size(h);
[Mx, Nx] = size(x);
center = round(size(h)/2);
D = fft2(circshift(padarray(h, [Mx - Mh, Nx - Nh], 'post'), 1 - center));
result = real(ifft2(D.*fft_x));

%c
reshap_x = reshape(x,[Mx*Nx,1]);

%h_ = zeros(3,3);
h_ = circshift(padarray(h, [Mx - Mh, Nx - Nh], 'post'), 1 - center);

C = h_ * reshap_x
