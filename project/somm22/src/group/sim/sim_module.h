/*
 *  \author ...
 */

#ifndef __SOMM22__MODULE__SIM__GROUP__
#define __SOMM22__MODULE__SIM__GROUP__

#include <stdint.h>

namespace somm22 
{
    namespace group
    {
        namespace sim
        {
            //need:
            //currentSimStep: number of events already processed
            //currentSimTime: current simulation time that increases based on the processed events
            //currentSimMask: bitwise-or representing possible types of events that can be processed
            extern uint32_t currentSimStep;
            extern uint32_t currentSimTime;
            extern uint32_t currentSimMask;

        } // end of namespace sim

    } // end of namespace group

} // end of namespace somm22

#endif /* __SOMM22__MODULE__SIM__GROUP__ */

